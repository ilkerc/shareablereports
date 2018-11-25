package io.rakam.shareablereports.converter

interface Converter<C,T> {
    fun convert(item : T) : C
}