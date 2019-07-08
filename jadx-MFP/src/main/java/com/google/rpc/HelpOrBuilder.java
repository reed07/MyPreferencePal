package com.google.rpc;

import com.google.protobuf.MessageOrBuilder;
import com.google.rpc.Help.Link;
import com.google.rpc.Help.LinkOrBuilder;
import java.util.List;

public interface HelpOrBuilder extends MessageOrBuilder {
    Link getLinks(int i);

    int getLinksCount();

    List<Link> getLinksList();

    LinkOrBuilder getLinksOrBuilder(int i);

    List<? extends LinkOrBuilder> getLinksOrBuilderList();
}
