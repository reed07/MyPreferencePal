package io.requery.sql.type;

import com.facebook.ads.AdError;
import io.requery.sql.BasicType;
import io.requery.sql.Keyword;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BlobType extends BasicType<Blob> {
    public BlobType() {
        super(Blob.class, AdError.INTERNAL_ERROR_2004);
    }

    public Blob fromResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getBlob(i);
    }

    public Keyword getIdentifier() {
        return Keyword.BLOB;
    }
}
