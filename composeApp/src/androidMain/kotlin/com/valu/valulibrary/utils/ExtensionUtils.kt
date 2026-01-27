package com.valu.valulibrary.utils

fun  String.htmlDriveMovie():String{
    return """
        <html>
            <body style="margin:0;padding:0;background-color:black;">
                <iframe 
                    src="$this" 
                    width="100%" 
                    height="100%" 
                    frameborder="0" 
                    allow="autoplay; encrypted-media" 
                    allowfullscreen>
                </iframe>
            </body>
        </html>
    """
}