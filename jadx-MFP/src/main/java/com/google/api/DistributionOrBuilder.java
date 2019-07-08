package com.google.api;

import com.google.api.Distribution.BucketOptions;
import com.google.api.Distribution.BucketOptionsOrBuilder;
import com.google.api.Distribution.Range;
import com.google.api.Distribution.RangeOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface DistributionOrBuilder extends MessageOrBuilder {
    long getBucketCounts(int i);

    int getBucketCountsCount();

    List<Long> getBucketCountsList();

    BucketOptions getBucketOptions();

    BucketOptionsOrBuilder getBucketOptionsOrBuilder();

    long getCount();

    double getMean();

    Range getRange();

    RangeOrBuilder getRangeOrBuilder();

    double getSumOfSquaredDeviation();

    boolean hasBucketOptions();

    boolean hasRange();
}
