#YouTube Title Grabber by Jeffrey Wilhite
#V1.3

#program functionality wishlist:
#find a better way to deal with URL input

#imports for later features:
#from urllib.parse import urlparse

#necessary imports
import argparse
import codecs
import os.path
from apiclient.discovery import build

#initialize global vars
V_titles = []
playlist_ID = ""
file_name = ""

# def OsFriendlyFileName:
	


parser = argparse.ArgumentParser(description='Grabs the titles of videos in a YouTube playlist and saves them to a file.')
parser.add_argument('URL', 
					action="store",
					help= "playlist URL to iterate through (string), <required>")
parser.add_argument('-KEY',
					"--DevKey",
					action="store",
					default= "",
					help= "Dev key for use of Google's API. If none is provided by the user, it will attempt to read from a file named: \"key.txt\" <required>")
parser.add_argument('-s', 
					'--save', 
					action="store_true", 
					dest="save", 
					default = True, 
					help= "save to file (boolean) Default = True")
parser.add_argument('-name', 
					"--file_name", 
					action="store", 
					dest="name", 
					default="YouTube Playlist Video Titles", 
					help= "string of desired filename (string). default = *playlist title*")


args = parser.parse_args()
playlist_url = args.URL
file_name = args.name
key=args.DevKey

if args.DevKey == "":
	print("No Dev-Key provided, searching for \'key.txt\' in local directory...")
	if os.path.isfile("key.txt"):
		print("DEV KEY imported from file.")
		file = open("key.txt", "r")
		key = file.readline()
		file.close()
	else:
		print("no valid DevKey provided")
		raise IOError("File: \"key.txt\" does not exist")

print("DEV KEY Accepted.")


#harvest the playlist ID out of the URL
if "/playlist?" in playlist_url:
	print("URL Accepted \n\r")
	split_url = playlist_url.split("list=")
	playlist_ID = split_url[1]
	print("Playlist_ID: " + playlist_ID)
else:
	print("URL is not a valid URL")


#create a playlist resource for playlist author and playlist title
playlist_service = build('youtube','v3',developerKey = key)
playlist_request = playlist_service.playlists().list(part="snippet", id=playlist_ID, maxResults=1)
playlist_response = playlist_request.execute()
file_name=playlist_response.get("items")[0].get('snippet').get('title') #pick out playlist title.
author_name=playlist_response.get("items")[0].get('snippet').get('channelTitle') #pick out playlist author name.
print("Playlist Title: " + file_name)
print("  ==>Author: " + author_name + "\r\n")

service = build('youtube','v3',developerKey = key)
snippit_request = service.playlistItems().list(part="snippet", playlistId=playlist_ID, maxResults=50)
snippit_response = snippit_request.execute()
#summons the first 50 items in the playlist...	

for item in snippit_response['items']:
	V_titles.append(item['snippet']['title'])
#^ grabs first 50 items in the playlist

# V--iterate until break condition is met though entire playlist and grab vid titles as long as the first request did not have a next page token--V
if snippit_response.get('nextPageToken') is not None:
	while True:
		#requests the next 50 items 
		snippit_request = service.playlistItems().list(part="snippet", playlistId=playlist_ID, maxResults=50, pageToken=snippit_response.get('nextPageToken'))
		snippit_response = snippit_request.execute()
		
		#grab the next 50 titles and add them to the list
		for item in snippit_response['items']:
			V_titles.append(item['snippet']['title'])
			
		#		V---loop break condition---V
		#...When there are no more parts to the list...
		if snippit_response.get('nextPageToken') is None:
			break


print(str(len(V_titles)) + " titles in list." + "\r\n")


#Builds and saves file with list of video titles if args.save==true.
if args.save:
	if file_name != "":
		try:
			file = codecs.open(file_name+".txt", "w", "utf-8")
		except:
			file_name = author_name+"\'s Playlist"
			print("Unusual Playlist name detected, \r\n Saving with alternate name of: " + file_name)
			file = codecs.open(file_name+".txt", "w", "utf-8")
	else:
		file_name = author_name+"\'s Playlist"
		print("Empty Playlist name detected, \r\n Saving with alternate name of: " + file_name)
		file = codecs.open(file_name+".txt", "w", "utf-8")
	
	file.write("****PLAYLIST ID: %s****\r\n" % playlist_ID)
	file.write("Playlist Author: " + author_name + "\r\n")
	file.write(str(len(V_titles)) + " titles in list. " +  "\r\n\r\n\r\n")
	if len(V_titles)>0:
		for title in V_titles:
			file.write(title + "\r\n")	
	else:
		file.write("There were no video\'s in this playlist...")
	file.close()

		
	print("All video titles retrieved and saved. \r\n  ==>File Name: " + file_name)