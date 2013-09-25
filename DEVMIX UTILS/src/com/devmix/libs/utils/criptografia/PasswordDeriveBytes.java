package com.devmix.libs.utils.criptografia;

import java.security.MessageDigest;

public class PasswordDeriveBytes
{
    private String _password;
    private byte[] _salt;
    private String _hashName;
    private int _iterations;

    public PasswordDeriveBytes(String password, byte[] salt)
    {
        _password = password;
        _salt = salt;
        _hashName = "SHA-1";
        _iterations = 100;
    }

    public PasswordDeriveBytes(String password, byte[] salt, String hashName, int iterations)
    {
        _password = password;
        _salt = salt;
        _hashName = hashName;
        _iterations = iterations;
    }

    public byte[] getBytes(int cb)
    {
        byte[] data = _password.getBytes();
        if (_salt != null)
        {
            byte[] temp = new byte[data.length + _salt.length];
            System.arraycopy(data, 0, temp, 0, data.length);
            System.arraycopy(_salt, 0, temp, data.length, _salt.length);
            data = temp;
        }

        MessageDigest md;
        try
        {
            md = MessageDigest.getInstance(_hashName);
        }
        catch (Exception e)
        {
            return null;
        }

        if (_iterations <= 0)
            _iterations = 1;

        byte[] key = new byte[cb];
        int size = 0;
        while (size < cb)
        {
            for (int i = 0; i < _iterations; i++)
                data = md.digest(data);
            int remaining = cb - size;
            remaining = (remaining < data.length ? remaining : data.length);
            System.arraycopy(data, 0, key, size, remaining);
            size += remaining;
        }

        return key;
    }
}