# BaseLayer

Base layer can be used for any mobile app to make development easier and faster , following clean architecture principles  

* Kotlin 
* MVVM
* Retrofit
* Corotuines
* Kodien

Provide centralized Error handling across the app, show dialog in case of api error or a full screen dialog with a retry button for no internet connection 

Provide centralized loading indicator which is displayed with each network call and dismissed in case of success / fail

listen to network state and show snackbar message based on connectivity (use job sechdualr instead of implicit broadcast reciver)
