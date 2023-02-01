![Project Image](https://raw.githubusercontent.com/DevThurain/BitmapAssistant/development/images/Bitmap%20Assistant.png)
### Gradle
[![Maven Central](https://img.shields.io/badge/maven--central-v1.0.1-blue)](https://search.maven.org/search?q=g:%22io.github.devthurain%22%20AND%20a:%22bitmapassistantlibrary%22)
Add the dependency below to your module's `build.gradle` file:
```gradle
dependencies {
    implementation "io.github.devthurain:bitmapassistantlibrary:1.0.1"
}
```


## Usage
You can convert Uri to Bitmap, Bitmap to File, Uri to File directly using following methods.

Uri -> Bitmap
```kotlin
val myUri: Uri? = ...
myUri.directConvertToBitmap(  
	  context = this,  
	  qualityScale = 0.25,  
	  onSuccess = { bitmap ->   
		// further operation  
  },  
	  onFailed = { message ->   
	        // error message   
 })
```
Uri -> File
```kotlin
val myUri: Uri? = ...
myUri.directConvertToCacheFile(  
	  context = this,  
	  fileName = "myFile",
	  qualityScale = 0.25,  
	  onSuccess = { bitmap ->   
		// further operation  
  },  
	  onFailed = { message ->   
		// error message   
 })
```
Bitmap -> File
```kotlin
val bitmap: Bitmap = ...
val file : File? = bitmap.directConvertToCacheFile(context = this, fileName = "myFile")
```

## Find this library useful? :heart:
Support it by giving start to this repository. :star: <br>
Also, **__[follow](https://github.com/devthurain)__** me for my next creations! 🤩

# License
```xml
Copyright 2019 skydoves (Jaewoong Eum)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
