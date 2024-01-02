# Java Code Dataset Building Application

This repository contains a small GUI application written in Java 
to extract code from a given set of projects for my master thesis 
in media computer science at the Cologne University of Applied Sciences.

This small application allows to extract every java method from a 
given folder (and its subfolders) to classify into two categories 
(clean and not clean). The user is presented a method and can then do 
the classification. After the decision the application stores the method 
for further processing.

This was used to build a dataset for the thesis to use it with a neural 
network to verify if some sort of binary classification based on common 
clean code patterns would be possible.

You can find the code for the neural network (and additional information 
about the master thesis) [here](https://github.com/slemke/master-thesis).
