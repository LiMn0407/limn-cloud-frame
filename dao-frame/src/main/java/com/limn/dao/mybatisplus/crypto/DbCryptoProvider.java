package com.limn.dao.mybatisplus.crypto;

public interface DbCryptoProvider {

    String encrypt(String value);

    String decrypt(String value);


}
