# OTP Auth

## usage

```
$ make native
$ ./otpauth.exe -i Github -a 'user@example.com' -s 'abcd efgh ijkl mnop'
```

## why
- 一直用 google authenticator 做 two-step validator
- 问题是 google authenticator 不能备份，数据迁移的时候很麻烦
- 方案之一，是生成的时候，把 secret 记录下来。迁移的时候，重新输入一次
- 在 google authenticator 里手动输入的时候，不支持 issuer。只能自己生成一个带 issuer 的二维码
- 于是有了这个项目
