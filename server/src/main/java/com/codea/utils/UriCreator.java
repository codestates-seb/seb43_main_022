package com.codea.utils;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class UriCreator {
    public static URI createUri(String defaultUrl, long resourceId) {
        return UriComponentsBuilder
                .newInstance()
                .path(defaultUrl + "/{resource-id}")
                .buildAndExpand(resourceId)
                .toUri();
    }

    public static URI createUri(String defaultUrl, String additionalUrl) {
        return UriComponentsBuilder
                .newInstance()
                .path(defaultUrl + "/{additionalUrl}")
                .buildAndExpand(additionalUrl)
                .toUri();
    }
}
