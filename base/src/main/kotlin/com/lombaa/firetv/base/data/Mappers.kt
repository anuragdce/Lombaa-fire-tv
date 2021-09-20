package com.lombaa.firetv.base.data

interface Mappable<T> {

    fun map(): T
}

interface MappableFrom<I, O> {

    fun mapFrom(input: I) : O
}