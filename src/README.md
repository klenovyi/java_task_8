# Компиляция и запуск из командной строки

Примеры команд - копия сеанса моего терминала linux. Для windows всё почти также.  

Перейти в директорию Task8_sample/src. 
Список директорий должен быть таким. (для windows команда dir а не ls)
```
~/IdeaProjects/Task8_sample/src$ ls
cmd  gui  logic  README.md
```
Создать директорию bin. (для скомпилированных классов)
```
~/IdeaProjects/Task8_sample/src$ mkdir bin
~/IdeaProjects/Task8_sample/src$ ls
bin  cmd  gui  logic  README.md
```
Компилируем все классы в директориях logic и cmd.  
~/.jdks/openjdk-19/bin/javac - полный путь до программы javac. В вашем случае это может быть просто javac или javac.exe или также полный путь  
Опция -d bin показывает директорию куда нужно поместить скомпилированные классы.  
После этого можно перейти в bin, убедиться, что там появились файлы .class, но потом вернуться назад.
```
~/IdeaProjects/Task8_sample/src$ ~/.jdks/openjdk-19/bin/javac -d bin logic/* cmd/*
```
Запускаем скомпилированные классы с помощью программы java.  
Опция -classpath bin показыает в какой директории находятся классы (пакет)  
cmd.Program - путь до класса в пакете который нужно запустить (функцию main в нём)
```
~/IdeaProjects/Task8_sample/src$ ~/.jdks/openjdk-19/bin/java -classpath bin cmd.Program
Использование: java l6.task8_sample.cmd.Program INPUT OUTPUT
Увеличивает каждый элемент целочисленного массива из файла INPUT на 1. Результат записывается в файл OUTPUT.

Использование: java l6.task8_sample.cmd.Program INPUT OUTPUT
Увеличивает каждый элемент целочисленного массива из файла INPUT на 1. Результат записывается в файл OUTPUT.

Ошибка разбора аргументов коммандной строки

```
Программа запустилась успешно. 

Создаём файл in.txt в текущей директории и записываем туда двумерный массив любым удобным способом. (например через блокнот. Я это делаю через команды echo).  
(команда cat в linux печатает содержимое файла)
```
~/IdeaProjects/Task8_sample/src$ echo "1 2 3" >> in.txt
~/IdeaProjects/Task8_sample/src$ echo "4 5 99" >> in.txt
~/IdeaProjects/Task8_sample/src$ ls
bin  cmd  gui  in.txt  logic  README.md
~/IdeaProjects/Task8_sample/src$ cat in.txt
1 2 3
4 5 99

```
Запускаем еще раз, передавая после класса путь до файла откуда нужно считать массив и файла куда нужно записать массив (.т.к. файл в текущей директории, то просто название)
```
~/IdeaProjects/Task8_sample/src$ ~/.jdks/openjdk-19/bin/java -classpath ./bin cmd.Program in.txt out.txt
```
Проверяем содержимое файла out.txt
```
~/IdeaProjects/Task8_sample/src$ ls
bin  cmd  gui  in.txt  logic  out.txt  README.md
~/IdeaProjects/Task8_sample/src$ cat out.txt
2 3 4
5 6 100
```