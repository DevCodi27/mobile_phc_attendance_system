import 'package:flutter/material.dart';

class CustomButton extends StatelessWidget {
    final String hint;
  final TextEditingController? controller;
  final bool obsecureText;
  const CustomButton({super.key,
  required this.hint,
   this.controller,
   this.obsecureText = false});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: TextField(
        obscureText: obsecureText,
        controller: controller,

        decoration: InputDecoration(
          filled: true,
                          hintText: hint,
                        fillColor: Colors.white,  
                  border: OutlineInputBorder(
                    borderSide: BorderSide(),
                    borderRadius:BorderRadius.circular(10) 
                    ),
                    
                    
                  ),
                  
        ),
      );
  }
}


