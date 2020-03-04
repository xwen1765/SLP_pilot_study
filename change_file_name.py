import os

for filename in os.listdir("/Users/wen/Desktop/American_recording"):
    new_filename = filename.replace('_a.WAV','.wav')
    print(new_filename)
    os.rename(r'/Users/wen/Desktop/American_recording/'+filename,r'/Users/wen/Desktop/American_recording/'+new_filename)
    print(filename)
