# moment
Parse, validate, manipulate, and display dates in Android.Inspired by momentjs.
## Usage
```
    new Moment().getLastMonday()   // the monday of this week
```
## Import
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.arover:moment:-SNAPSHOT'
	}
