# Keystore Utils
Adalah API yang gunanya untuk storing Credential Key secara aman di satu kontainer khusus. Dengan menggunakan Keystore, secret key yang digunakan untuk encryption dan decryption dapat tersimpan secara aman.

## Cara Enkripsi
Untuk mengenkripsi diperlukan 2 parameter, yaitu `keyAlias` dan `data`. 
| Nama | Deskripsi | Contoh |
|--|--|--|
| `keyAlias` | sebagai penanda atau label dari key | `TOKEN` |
| `data` | diisi data yang ingin di enkripsi | `QwejaLWQJElkajsdqwe` |

## Cara Dekripsi
Untuk mengenkripsi diperlukan 2 parameter, yaitu `keyAlias` dan `cipherText`. 
| Nama | Deskripsi | Contoh |
|--|--|--|
| `keyAlias` | sebagai penanda atau label dari key | `TOKEN` |
| `cipherText` | diisi data yang ingin di dekripsi | `QwejaLWQJElkajsdqwe` |

## Catatan
File utilias pada repository ini hanya digunakan untuk enkripsi dan dekripsi data secara aman. Disarankan data yang telah dienkripsi dapat disimpan lagi di penyimpanan lain (Misal Datastore, EncryptedSharedPreferences, dll). Hal ini disarankan agar data tersebut dapat didekripsi kembali di waktu kemudian.
 

 
