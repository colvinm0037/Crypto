# Crypto

Contains my implementations of two well-known attacks on RSA.

Both methods take the publicly available numbers N and e that are part of a RSA system and use them to try and 
break the system by discovering d.

N = p * q (p and q are the primes selected by the user/system setting up the RSA system).
e is the public encryption exponent.
d is the private decryption exponent.

Both attacks are limited in use and aren't meant to be able to break every RSA encryption. They both rely on weaknesses that 
do occur over time in the real world. The idea would be to attempt one of these methods over a large number of keys to find
one that is breakable. 

Both of these attacks have been known for a long time and anyone who is knowledgable about RSA should be able to avoid them. 
The problem occurs when people don't really know what they are doing or are under the mistaken idea that RSA can never be 
broken.

######Pollard's Attack
https://en.wikipedia.org/wiki/Pollard%27s_p_%E2%88%92_1_algorithm

######Wiener's Attack
https://en.wikipedia.org/wiki/Wiener%27s_attack

######Notes on the code:
I created these about 1.5 years ago for a math class. The code was a little ugly but worked well. I cleaned them up and
added test cases. They aren't impervious to error as I haven't done anything to validate input. If you wan't to see it working
then running some of the test cases is a good place to start. Otherwise you can run the main interface, but coming up with 
numbers that work may be a little challenging.
