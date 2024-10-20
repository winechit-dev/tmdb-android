# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.tmdb.data.model.** { *; }
-keep class com.tmdb.datastore.model.** { *; }
-keep class com.tmdb.domain.model.** { *; }
-keep class com.tmdb.ui.model.** { *; }
-keep class com.tmdb.discover.model.** { *; }
-keep class com.tmdb.search.model.** { *; }
-keep class com.tmdb.search.model.** { *; }

##--------------- OKHTTP3 ------------
#Source : https://github.com/square/okhttp/
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
##--------------- OKHTTP3 ------------


##--------------- Retrofit2 ------------
#Source : https://github.com/square/retrofit#proguard
-dontwarn okio.**
-dontwarn javax.annotation.**
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }

# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
 -keep,allowobfuscation,allowshrinking interface retrofit2.Call
 -keep,allowobfuscation,allowshrinking class retrofit2.Response

 # With R8 full mode generic signatures are stripped for classes that are not
 # kept. Suspend functions are wrapped in continuations where the type argument
 # is used.
 -keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
##--------------- Retrofit2 ------------

##--------------- protobuf ------------
-keep class * extends com.google.protobuf.GeneratedMessageLite { *; }

##--------------- sqlcipher ------------
-keep class net.sqlcipher.** {
    *;
}