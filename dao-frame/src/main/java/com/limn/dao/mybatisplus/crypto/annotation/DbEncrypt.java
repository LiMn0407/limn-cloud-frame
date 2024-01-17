package com.limn.dao.mybatisplus.crypto.annotation;

import com.limn.dao.mybatisplus.crypto.DbCryptoProvider;
import com.limn.dao.mybatisplus.crypto.DbDefaultCryptoProvider;

public @interface DbEncrypt {

    Class<? extends DbCryptoProvider> using() default DbDefaultCryptoProvider.class;

}
