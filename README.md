# OTP Auth

## usage

```
$ cargo build --release
$ ./target/release/otpauth --help
$ ./target/release/otpauth -a 'Example' -i 'alice@google.com' -s 'JBSW Y3DP EHPK 3PXP'
$ ./target/release/otpauth -a 'Example' -i 'alice@google.com' -s 'JBSW Y3DP EHPK 3PXP' --svg > otp.svg
```

## why
- google authenticator 不支持导出，数据迁移时很麻烦
- 方案之一，生成时把 secret 记录下来，迁移时重新输入
- 在 google authenticator 里手动输入时，不支持 issuer
- 于是有了这个项目，自己生成一个带 issuer 的二维码

---

最初是 kotlin + graal，后来写了一个 dart，目前是 rust。
确实是 rust 最舒服哇。
