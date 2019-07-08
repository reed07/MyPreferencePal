package io.requery.sql;

import io.requery.Converter;
import io.requery.converter.CurrencyConverter;
import io.requery.converter.EnumStringConverter;
import io.requery.converter.LocalDateConverter;
import io.requery.converter.LocalDateTimeConverter;
import io.requery.converter.LocalTimeConverter;
import io.requery.converter.OffsetDateTimeConverter;
import io.requery.converter.URIConverter;
import io.requery.converter.URLConverter;
import io.requery.converter.UUIDConverter;
import io.requery.converter.ZonedDateTimeConverter;
import io.requery.meta.Attribute;
import io.requery.query.Expression;
import io.requery.query.ExpressionType;
import io.requery.query.function.Function;
import io.requery.query.function.Function.Name;
import io.requery.sql.type.BigIntType;
import io.requery.sql.type.BinaryType;
import io.requery.sql.type.BlobType;
import io.requery.sql.type.BooleanType;
import io.requery.sql.type.ClobType;
import io.requery.sql.type.DateType;
import io.requery.sql.type.DecimalType;
import io.requery.sql.type.FloatType;
import io.requery.sql.type.IntegerType;
import io.requery.sql.type.JavaDateType;
import io.requery.sql.type.PrimitiveBooleanType;
import io.requery.sql.type.PrimitiveByteType;
import io.requery.sql.type.PrimitiveDoubleType;
import io.requery.sql.type.PrimitiveFloatType;
import io.requery.sql.type.PrimitiveIntType;
import io.requery.sql.type.PrimitiveLongType;
import io.requery.sql.type.PrimitiveShortType;
import io.requery.sql.type.RealType;
import io.requery.sql.type.SmallIntType;
import io.requery.sql.type.TimeStampType;
import io.requery.sql.type.TimeType;
import io.requery.sql.type.TinyIntType;
import io.requery.sql.type.VarBinaryType;
import io.requery.sql.type.VarCharType;
import io.requery.util.ClassMap;
import io.requery.util.LanguageVersion;
import io.requery.util.Objects;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;

public class GenericMapping implements Mapping {
    private final ClassMap<Converter<?, ?>> converters;
    private final ClassMap<FieldType> fixedTypes;
    private final ClassMap<Name> functionTypes;
    private PrimitiveBooleanType primitiveBooleanType = new BooleanType(Boolean.TYPE);
    private PrimitiveByteType primitiveByteType = new TinyIntType(Byte.TYPE);
    private PrimitiveDoubleType primitiveDoubleType = new RealType(Double.TYPE);
    private PrimitiveFloatType primitiveFloatType = new FloatType(Float.TYPE);
    private PrimitiveIntType primitiveIntType = new IntegerType(Integer.TYPE);
    private PrimitiveLongType primitiveLongType = new BigIntType(Long.TYPE);
    private PrimitiveShortType primitiveShortType = new SmallIntType(Short.TYPE);
    private final Map<Attribute, FieldType> resolvedTypes;
    private final ClassMap<FieldType> types = new ClassMap<>();

    public GenericMapping(Platform platform) {
        this.types.put(Boolean.TYPE, new BooleanType(Boolean.TYPE));
        this.types.put(Boolean.class, new BooleanType(Boolean.class));
        this.types.put(Integer.TYPE, new IntegerType(Integer.TYPE));
        this.types.put(Integer.class, new IntegerType(Integer.class));
        this.types.put(Short.TYPE, new SmallIntType(Short.TYPE));
        this.types.put(Short.class, new SmallIntType(Short.class));
        this.types.put(Byte.TYPE, new TinyIntType(Byte.TYPE));
        this.types.put(Byte.class, new TinyIntType(Byte.class));
        this.types.put(Long.TYPE, new BigIntType(Long.TYPE));
        this.types.put(Long.class, new BigIntType(Long.class));
        this.types.put(Float.TYPE, new FloatType(Float.TYPE));
        this.types.put(Float.class, new FloatType(Float.class));
        this.types.put(Double.TYPE, new RealType(Double.TYPE));
        this.types.put(Double.class, new RealType(Double.class));
        this.types.put(BigDecimal.class, new DecimalType());
        this.types.put(byte[].class, new VarBinaryType());
        this.types.put(Date.class, new JavaDateType());
        this.types.put(java.sql.Date.class, new DateType());
        this.types.put(Time.class, new TimeType());
        this.types.put(Timestamp.class, new TimeStampType());
        this.types.put(String.class, new VarCharType());
        this.types.put(Blob.class, new BlobType());
        this.types.put(Clob.class, new ClobType());
        this.fixedTypes = new ClassMap<>();
        this.fixedTypes.put(byte[].class, new BinaryType());
        this.functionTypes = new ClassMap<>();
        this.converters = new ClassMap<>();
        this.resolvedTypes = new IdentityHashMap();
        HashSet<Converter> hashSet = new HashSet<>();
        hashSet.add(new EnumStringConverter(Enum.class));
        hashSet.add(new UUIDConverter());
        hashSet.add(new URIConverter());
        hashSet.add(new URLConverter());
        hashSet.add(new CurrencyConverter());
        if (LanguageVersion.current().atLeast(LanguageVersion.JAVA_1_8)) {
            hashSet.add(new LocalDateConverter());
            hashSet.add(new LocalTimeConverter());
            hashSet.add(new LocalDateTimeConverter());
            hashSet.add(new ZonedDateTimeConverter());
            hashSet.add(new OffsetDateTimeConverter());
        }
        platform.addMappings(this);
        for (Converter converter : hashSet) {
            Class mappedType = converter.getMappedType();
            if (!this.types.containsKey(mappedType)) {
                this.converters.put(mappedType, converter);
            }
        }
    }

    public Mapping aliasFunction(Name name, Class<? extends Function> cls) {
        this.functionTypes.put(cls, name);
        return this;
    }

    public <T> Mapping replaceType(int i, FieldType<T> fieldType) {
        Objects.requireNotNull(fieldType);
        replace(this.types, i, fieldType);
        replace(this.fixedTypes, i, fieldType);
        return this;
    }

    private void replace(ClassMap<FieldType> classMap, int i, FieldType fieldType) {
        LinkedHashSet<Class> linkedHashSet = new LinkedHashSet<>();
        for (Entry entry : classMap.entrySet()) {
            if (((FieldType) entry.getValue()).getSqlType() == i) {
                linkedHashSet.add(entry.getKey());
            }
        }
        for (Class put : linkedHashSet) {
            classMap.put(put, fieldType);
        }
        if (i == this.primitiveIntType.getSqlType() && (fieldType instanceof PrimitiveIntType)) {
            this.primitiveIntType = (PrimitiveIntType) fieldType;
        } else if (i == this.primitiveLongType.getSqlType() && (fieldType instanceof PrimitiveLongType)) {
            this.primitiveLongType = (PrimitiveLongType) fieldType;
        } else if (i == this.primitiveShortType.getSqlType() && (fieldType instanceof PrimitiveShortType)) {
            this.primitiveShortType = (PrimitiveShortType) fieldType;
        } else if (i == this.primitiveBooleanType.getSqlType() && (fieldType instanceof PrimitiveBooleanType)) {
            this.primitiveBooleanType = (PrimitiveBooleanType) fieldType;
        } else if (i == this.primitiveFloatType.getSqlType() && (fieldType instanceof PrimitiveFloatType)) {
            this.primitiveFloatType = (PrimitiveFloatType) fieldType;
        } else if (i == this.primitiveDoubleType.getSqlType() && (fieldType instanceof PrimitiveDoubleType)) {
            this.primitiveDoubleType = (PrimitiveDoubleType) fieldType;
        } else if (i == this.primitiveByteType.getSqlType() && (fieldType instanceof PrimitiveByteType)) {
            this.primitiveByteType = (PrimitiveByteType) fieldType;
        }
    }

    public <T> Mapping putType(Class<? super T> cls, FieldType<T> fieldType) {
        if (cls == null) {
            throw new IllegalArgumentException();
        } else if (fieldType != null) {
            this.types.put(cls, fieldType);
            return this;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /* access modifiers changed from: 0000 */
    public Converter<?, ?> converterForType(Class<?> cls) {
        Converter<?, ?> converter = (Converter) this.converters.get(cls);
        return (converter != null || !cls.isEnum()) ? converter : (Converter) this.converters.get(Enum.class);
    }

    public FieldType mapAttribute(Attribute<?, ?> attribute) {
        FieldType fieldType = (FieldType) this.resolvedTypes.get(attribute);
        if (fieldType != null) {
            return fieldType;
        }
        Class classType = attribute.getClassType();
        if (attribute.isAssociation() && attribute.getReferencedAttribute() != null) {
            classType = ((Attribute) attribute.getReferencedAttribute().get()).getClassType();
        }
        if (attribute.getConverter() != null) {
            classType = attribute.getConverter().getPersistedType();
        }
        FieldType substitutedType = getSubstitutedType(classType);
        this.resolvedTypes.put(attribute, substitutedType);
        return substitutedType;
    }

    public Name mapFunctionName(Function<?> function) {
        Name name = (Name) this.functionTypes.get(function.getClass());
        return name != null ? name : function.getFunctionName();
    }

    private FieldType getSubstitutedType(Class<?> cls) {
        Converter converterForType = converterForType(cls);
        FieldType fieldType = null;
        if (converterForType != null) {
            if (converterForType.getPersistedSize() != null) {
                fieldType = (FieldType) this.fixedTypes.get(converterForType.getPersistedType());
            }
            cls = converterForType.getPersistedType();
        }
        if (fieldType == null) {
            fieldType = (FieldType) this.types.get(cls);
        }
        return fieldType == null ? new VarCharType() : fieldType;
    }

    public <A> A read(Expression<A> expression, ResultSet resultSet, int i) throws SQLException {
        FieldType fieldType;
        Class cls;
        Converter converter;
        if (expression.getExpressionType() == ExpressionType.ATTRIBUTE) {
            Attribute attribute = (Attribute) expression;
            converter = attribute.getConverter();
            cls = attribute.getClassType();
            fieldType = mapAttribute(attribute);
        } else {
            cls = expression.getClassType();
            fieldType = getSubstitutedType(cls);
            converter = null;
        }
        boolean isPrimitive = cls.isPrimitive();
        if (converter == null && !isPrimitive) {
            converter = converterForType(cls);
        }
        Object read = fieldType.read(resultSet, i);
        if (isPrimitive && resultSet.wasNull()) {
            read = null;
        }
        if (converter != null) {
            read = toMapped(converter, cls, read);
        }
        if (isPrimitive) {
            return read;
        }
        return cls.cast(read);
    }

    public boolean readBoolean(ResultSet resultSet, int i) throws SQLException {
        return this.primitiveBooleanType.readBoolean(resultSet, i);
    }

    public byte readByte(ResultSet resultSet, int i) throws SQLException {
        return this.primitiveByteType.readByte(resultSet, i);
    }

    public short readShort(ResultSet resultSet, int i) throws SQLException {
        return this.primitiveShortType.readShort(resultSet, i);
    }

    public int readInt(ResultSet resultSet, int i) throws SQLException {
        return this.primitiveIntType.readInt(resultSet, i);
    }

    public long readLong(ResultSet resultSet, int i) throws SQLException {
        return this.primitiveLongType.readLong(resultSet, i);
    }

    public float readFloat(ResultSet resultSet, int i) throws SQLException {
        return this.primitiveFloatType.readFloat(resultSet, i);
    }

    public double readDouble(ResultSet resultSet, int i) throws SQLException {
        return this.primitiveDoubleType.readDouble(resultSet, i);
    }

    public <A> void write(Expression<A> expression, PreparedStatement preparedStatement, int i, A a) throws SQLException {
        Class cls;
        FieldType fieldType;
        Converter converter;
        if (expression.getExpressionType() == ExpressionType.ATTRIBUTE) {
            Attribute attribute = (Attribute) expression;
            converter = attribute.getConverter();
            fieldType = mapAttribute(attribute);
            if (attribute.isAssociation()) {
                cls = ((Attribute) attribute.getReferencedAttribute().get()).getClassType();
            } else {
                cls = attribute.getClassType();
            }
        } else {
            cls = expression.getClassType();
            fieldType = getSubstitutedType(cls);
            converter = null;
        }
        if (converter == null && !cls.isPrimitive()) {
            converter = converterForType(cls);
        }
        if (converter != null) {
            a = converter.convertToPersisted(a);
        }
        fieldType.write(preparedStatement, i, a);
    }

    public void writeBoolean(PreparedStatement preparedStatement, int i, boolean z) throws SQLException {
        this.primitiveBooleanType.writeBoolean(preparedStatement, i, z);
    }

    public void writeByte(PreparedStatement preparedStatement, int i, byte b) throws SQLException {
        this.primitiveByteType.writeByte(preparedStatement, i, b);
    }

    public void writeShort(PreparedStatement preparedStatement, int i, short s) throws SQLException {
        this.primitiveShortType.writeShort(preparedStatement, i, s);
    }

    public void writeInt(PreparedStatement preparedStatement, int i, int i2) throws SQLException {
        this.primitiveIntType.writeInt(preparedStatement, i, i2);
    }

    public void writeLong(PreparedStatement preparedStatement, int i, long j) throws SQLException {
        this.primitiveLongType.writeLong(preparedStatement, i, j);
    }

    public void writeFloat(PreparedStatement preparedStatement, int i, float f) throws SQLException {
        this.primitiveFloatType.writeFloat(preparedStatement, i, f);
    }

    public void writeDouble(PreparedStatement preparedStatement, int i, double d) throws SQLException {
        this.primitiveDoubleType.writeDouble(preparedStatement, i, d);
    }

    public void addConverter(Converter<?, ?> converter, Class<?>... clsArr) {
        this.converters.put(converter.getMappedType(), converter);
        for (Class<?> put : clsArr) {
            this.converters.put(put, converter);
        }
    }

    private static <A, B> A toMapped(Converter<A, B> converter, Class<? extends A> cls, B b) {
        return converter.convertToMapped(cls, b);
    }
}
