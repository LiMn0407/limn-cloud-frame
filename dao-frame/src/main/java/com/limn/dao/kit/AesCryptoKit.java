package com.limn.dao.kit;

import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.extra.spring.SpringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class AesCryptoKit {

    private static final ThreadLocal<AES> AES = ThreadLocal.withInitial(() -> {
        String aesTextKey = SpringUtil.getProperty("data.crypto.aes.key");
        return !StringUtils.isBlank(aesTextKey) && aesTextKey.length() == 16 ? new AES(Mode.ECB, Padding.PKCS5Padding, aesTextKey.getBytes()) : null;
    });

    public static String decrypt(String value){
        if (StringUtils.isBlank(value)){
            return value;
        }
        AES aes = AES.get();
        Assert.isNull(aes,"请配置16位AES加密Key", new Object());
        return aes.decryptStr(value);
    }


}
