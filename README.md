# UniAccounts: نظام إدارة حسابات الطلبة

مشروع تطبيقي لمادة نماذج التصميم (Design Patterns) بلغة Java.

## المتطلبات

- JDK 17 أو أحدث (تحقق بالأمر `javac -version`).

## هيكل المشروع

```text
src/
├── Main.java                  نقطة التشغيل والتجربة
├── model/
│   ├── StudentAccount.java           الصنف الناتج (Product)
│   └── StudentAccountBuilder.java    Builder
├── audit/
│   └── AuditLog.java                 Singleton (سجل التدقيق)
├── repository/
│   └── AccountRepository.java        Singleton (المستودع المركزي)
└── prototype/
    └── AccountPrototype.java         Prototype (نسخ قالب الدفعة)
```

## الأنماط المستعملة (العمل الأول)

| النمط | الصنف | الغرض |
|---|---|---|
| Builder | `StudentAccountBuilder` | بناء الحساب بحقوله الثمانية دون مُنشئ طويل |
| Singleton | `AccountRepository`, `AuditLog` | نسخة واحدة من المستودع وسجل التدقيق |
| Prototype | `AccountPrototype` | نسخ قالب الدفعة وتغيير الهوية فقط |

## التشغيل

من مجلد `UniAccounts` نفسه:

```bash
javac -d out -sourcepath src src/Main.java
java -cp out Main
```

- الأمر الأول يترجم الشيفرة إلى مجلد `out`.
- الأمر الثاني يشغّل البرنامج.

## ملاحظات

- الصنف `LegacyStudentSystem` (في العمل الثاني) لا يُعدَّل.
- Git: الوسوم `1work` للعمل الأول و`2work` للعمل الثاني.