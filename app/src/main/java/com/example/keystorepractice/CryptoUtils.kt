package com.example.keystorepractice

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec


class CryptoUtils {
    private val ks = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }
    private val transformation = "AES/CBC/PKCS7Padding"
    private val String.toPreservedByteArray: ByteArray
        get() {
            return this.toByteArray(Charsets.ISO_8859_1)
        }
    private val ByteArray.toPreservedString: String
        get() {
            return String(this, Charsets.ISO_8859_1)
        }

    private fun generateKey(alias: String) {
        val generator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES,
            "AndroidKeyStore"
        ).apply {
            init(
                KeyGenParameterSpec.Builder(
                    alias,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build()
            )
        }

        generator.generateKey()
    }

    private fun getKey(alias: String): SecretKey {
        if (!ks.containsAlias(alias)) {
            generateKey(alias)
        }

        val entry = ks.getEntry(alias, null) as KeyStore.SecretKeyEntry
        return entry.secretKey
    }

    fun encrypt(aliasKey: String, data: String): String {
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.ENCRYPT_MODE, getKey(aliasKey))
        val iv = cipher.iv
        val cipherText = cipher.doFinal(data.encodeToByteArray())
        val combined = ByteArray(iv.size + cipherText.size)
        System.arraycopy(iv, 0, combined, 0, iv.size)
        System.arraycopy(cipherText, 0, combined, iv.size, cipherText.size)
        return combined.toPreservedString
    }

    fun decrypt(aliasKey: String, cipherText: String): String {
        val cipher = Cipher.getInstance(transformation)
        val iv = ByteArray(16)
        val arr = cipherText.toPreservedByteArray
        val ciphertext = ByteArray(arr.size - 16)
        System.arraycopy(arr, 0, iv, 0, iv.size)
        System.arraycopy(arr, iv.size, ciphertext, 0, ciphertext.size)
        cipher.init(Cipher.DECRYPT_MODE, getKey(aliasKey), IvParameterSpec(iv))
        return cipher.doFinal(ciphertext).toPreservedString
    }


}