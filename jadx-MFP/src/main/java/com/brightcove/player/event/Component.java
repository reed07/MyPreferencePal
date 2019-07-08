package com.brightcove.player.event;

@ListensFor(events = {"play", "stop"})
@Emits(events = {"sample", "event", "list"})
public interface Component {
}
