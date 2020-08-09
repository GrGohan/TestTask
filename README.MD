<h4>Расположение:</h4>
Основной файл проекта с расширением <b>TestTask.jar</b> содержится в папке <b>out</b>: <br>
<b>out\TestTask.jar</b>

<h4>Инструкция по запуску из командной строки:</h4>
В качестве аргумента jar-файлу необходимо передать файл расширения <b>*.properties</b> со значениями полей класса, например:
<br>
<code>java -jar TestTask.jar fields.properties</code><br>
<br>
В папке <b>out</b> содержится три заготовленных файла с тестовыми значениями полей класса:<br>
<code>fields.properties</code> - все поля заполнены значениями;<br>
<code>fields2.properties</code> - некоторые поля закомменчены для проверки заполняемости значениями по умолчанию;<br>
<code>fields3(error).properties</code> - в поле number допущена ошибка для проверки логирования.<br>


<h4>Пример файла *.properties:</h4>
<code># Имя параметра = значение параметра</code><br>
<code>com.mycompany.name = SuperSoft</code><br>
<code>com.mycompany.address = {street: Pushkina, home: Kolotushkina}</code><br>
<code>com.mycompany.owner = Andrew</code><br>
<code>com.mycompany.number = 1</code><br>
