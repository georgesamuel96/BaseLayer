package com.saraelmoghazy.base.baselayer;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Sara Elmoghazy
 */
@IntDef({ErrorType.NETWORK, ErrorType.HTTP, ErrorType.UNEXPECTED})
@Retention(RetentionPolicy.RUNTIME)
public @interface ErrorType {
    int NETWORK = 1;
    int HTTP = 2;
    int UNEXPECTED = 3;
}
