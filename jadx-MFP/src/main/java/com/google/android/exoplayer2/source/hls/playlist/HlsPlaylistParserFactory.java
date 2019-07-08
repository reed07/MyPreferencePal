package com.google.android.exoplayer2.source.hls.playlist;

import com.google.android.exoplayer2.upstream.ParsingLoadable.Parser;

public interface HlsPlaylistParserFactory {
    Parser<HlsPlaylist> createPlaylistParser();

    Parser<HlsPlaylist> createPlaylistParser(HlsMasterPlaylist hlsMasterPlaylist);
}
