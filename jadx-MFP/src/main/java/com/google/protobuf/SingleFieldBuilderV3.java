package com.google.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessage.Builder;
import com.google.protobuf.MessageOrBuilder;

public class SingleFieldBuilderV3<MType extends AbstractMessage, BType extends Builder, IType extends MessageOrBuilder> implements BuilderParent {
    private BType builder;
    private boolean isClean;
    private MType message;
    private BuilderParent parent;

    public SingleFieldBuilderV3(MType mtype, BuilderParent builderParent, boolean z) {
        this.message = (AbstractMessage) Internal.checkNotNull(mtype);
        this.parent = builderParent;
        this.isClean = z;
    }

    public void dispose() {
        this.parent = null;
    }

    public MType getMessage() {
        if (this.message == null) {
            this.message = (AbstractMessage) this.builder.buildPartial();
        }
        return this.message;
    }

    public MType build() {
        this.isClean = true;
        return getMessage();
    }

    public BType getBuilder() {
        if (this.builder == null) {
            this.builder = (Builder) this.message.newBuilderForType(this);
            this.builder.mergeFrom((Message) this.message);
            this.builder.markClean();
        }
        return this.builder;
    }

    public IType getMessageOrBuilder() {
        Object obj = this.builder;
        if (obj != null) {
            return obj;
        }
        return this.message;
    }

    public SingleFieldBuilderV3<MType, BType, IType> setMessage(MType mtype) {
        this.message = (AbstractMessage) Internal.checkNotNull(mtype);
        BType btype = this.builder;
        if (btype != null) {
            btype.dispose();
            this.builder = null;
        }
        onChanged();
        return this;
    }

    public SingleFieldBuilderV3<MType, BType, IType> mergeFrom(MType mtype) {
        if (this.builder == null) {
            MType mtype2 = this.message;
            if (mtype2 == mtype2.getDefaultInstanceForType()) {
                this.message = mtype;
                onChanged();
                return this;
            }
        }
        getBuilder().mergeFrom((Message) mtype);
        onChanged();
        return this;
    }

    public SingleFieldBuilderV3<MType, BType, IType> clear() {
        MType mtype;
        MType mtype2 = this.message;
        if (mtype2 != null) {
            mtype = mtype2.getDefaultInstanceForType();
        } else {
            mtype = this.builder.getDefaultInstanceForType();
        }
        this.message = (AbstractMessage) mtype;
        BType btype = this.builder;
        if (btype != null) {
            btype.dispose();
            this.builder = null;
        }
        onChanged();
        return this;
    }

    private void onChanged() {
        if (this.builder != null) {
            this.message = null;
        }
        if (this.isClean) {
            BuilderParent builderParent = this.parent;
            if (builderParent != null) {
                builderParent.markDirty();
                this.isClean = false;
            }
        }
    }

    public void markDirty() {
        onChanged();
    }
}
