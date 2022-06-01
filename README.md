# fl-2022-itmo-spr-L-IDE
Поддержка языка L в IDEA (https://github.com/kajigor/fl-2022-itmo-spr/blob/proj/lang/L.md)

## Парсер языка L при помощи ANTLR:
```diff
+ Successfully parsed
```

Success 1
```
my_func_test(arg1, arg2) 
{
if (!x) 
a =2
else {
x = 2;
a = 0b010;
var_123 = 0
};
skip}

main () {}
```
Success 2
```
main() {
  print # 
  int = 2;
  a = 2.0;
  b = 0b101010;
  d 
  =
  1 + 2 + 
  3 + 4 * 5 
  ^
  2;
  
  x = (a + 2) * d 
}
```

Success 3 

```
f(){}g(){skip}h(){skip;skip;skip}

x(x) {}
y(x, y) {}
z(x, y, z) {}

ll
(
)
{
}

ll_1
(
ll
)
{
ll
(
)
}

main() {
}

func_while(a) {
    ll();
    skip;
    # commment
    a = 2.4555;
    while (a > 0)
    {a = a - 1; b = a * 2 ^ 4}
    
}

func_if(a) {
    a = 0b1101;
    if (!a && b)
    c = 2
    else 
    a = 2
    if (a)
    a = 2;
    b = "String"
}
```


```diff
- Didn't parse
```
