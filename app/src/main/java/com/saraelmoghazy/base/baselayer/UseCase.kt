package com.saraelmoghazy.base.baselayer

/**
 * Annotation to identify use case with id.
 */

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class UseCase(val value: Int = 0)
