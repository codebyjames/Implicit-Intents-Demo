# Implicit-Intents-Demo

This Demo is to showcase how to replace the deprecated startActivityForResult() code with registerForActivityResult()

- startActivityForResult uses a request code and requires overriding onActivityResult to access the data
- registerForActivityResult uses the result from running an intent and then accessing that data

EXAMPLE BELOW : DIFFERENCE BETWEEN THE TWO

// use this intent as an example
```
val intent = Intent(Intent.ACTION_GET_CONTENT)
intent.type = "image/*"
```

START ACTIVITY FOR RESULT (deprecated)
ex. 
```
startActivityForResult(intent, REQUEST_CODE)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            val uri = data?.data
            uri?.let {
                // do whatever with the data
            }
        }
    }
 ```

REGISTER FOR ACTIVITY RESULT
ex. 
```
val startActivityNew = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
                val intent: Intent? = result.data
                val data = intent?.data
                data?.let {
                    // do whatever with the data
                }
         }
     }
     
     startActivityNew.launch(intent)
 ```

![alt text](https://github.com/codebyjames/Implicit-Intents-Demo/blob/main/shot_1.jpg)
![alt text](https://github.com/codebyjames/Implicit-Intents-Demo/blob/main/shot_2.jpg)
