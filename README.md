# Moment
Parse, validate, manipulate, and display dates in android. Inspired by momentjs.
## Usage
```
    Moment moment = new Moment()   // create a moment with default calendar instance.
    moment.toString() //"2016-08-29T08:02:17+08:00" (ISO 8601)

    // Edit
    // change moment time
    moment.edit().minute(23).hour(14);
    moment.fields().hour(); //=> 14
    moment.fields().minute(); //=> 23
        
    // Query
    moment.query().isBefore(othermoment);
    moment.query().lastMonday(); // the latest monday of this moment.
    moment.query().firstDayOfMonth(); // the first day of month of this moment.
    moment.query().isLeapYear();// false if  current year is not leap year

    // Field
    // get some field
    moment.fields().year(); // => 2016
    moment.fields().month(); // => 7
    // ....

    // Edit
    // manipulate moment,edit will change moment itself.
    moment.edit().setMinute(23);
    moment.edit().setHour(14);
    moment.edit().minus(2, MomentUnit.DAY);

    // Display
    // show moment as string.
    moment.display().format();
    
    // Convert
    // convert to normal java date classes.
    moment.getCalendar();
    moment.getDate();

```
## Import
Add it in your root build.gradle at the end of repositories:
```groovy
    allprojects {
        repositories {
        
            //...
            maven { url "https://jitpack.io" }
        }
    }
```
Step 2. Add the dependency
```groovy
    dependencies {
        compile 'com.github.arover:moment:-SNAPSHOT'
    }
```
