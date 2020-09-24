# OTP Auth

## usage

```
$ go build
$ ./otpauth -h
$ ./otpauth -i 'Example' -a 'alice@google.com' -s 'JBSW Y3DP EHPK 3PXP'
```

## why
- google authenticator 不支持导出，数据迁移时很麻烦
- 方案之一，生成时把 secret 记录下来，迁移时重新输入
- 在 google authenticator 里手动输入时，不支持 issuer
- 于是有了这个项目，自己生成一个带 issuer 的二维码

---

从 kotlin + graal 到 dart 到 rust 到 go。
rust 很棒，其实 go 也不错。
