<h4>Расположение:</h4>
Основной файл проекта с расширением .jar - <b>TestTask.jar</b> содержится в папке <b>out</b> 
<b>out\TestTask.jar</b>

<h4>Инструкция по запуску из командной строки:</h4>
В качестве аргумента jar-файлу необходимо передать файл расширения <b>*.properties</b> со значениями полей класса, например:
<br>
<code>java -jar TestTask.jar fields.properties</code><br>
<br>
В папке <b>out</b> содержится три заготовленных файла с тестовыми значениями полей класса:
fields.properties - все поля заполнены значениями;
fields2.properties - некоторые поля закомменчены для проверки заполняемости значениями по умолчанию;
fields3(error).properties - в поле number допущена ошибка для проверки логирования.

<h5>Синтаксис файла *.properties</h5>
<code># Имя параметра = значение параметра</code><br>
<code>com.mycompany.name = SuperSoft</code><br>
<code>com.mycompany.address = {street: Pushkina, home: Kolotushkina}</code><br>
<code>com.mycompany.owner = Andrew</code><br>
<code>com.mycompany.number = 1</code><br>
