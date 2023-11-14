import requests
import json

headers = {
    "Content-Type": "application/json",
    "Accept": "*/*"
}

while True:
    id = input('Enter poll ID: ')
    get_url = f'http://localhost:8080/poll/{id}'
    post_url = f'http://localhost:8080/poll/{id}'
    x = requests.get(get_url)

    if x.status_code != 200:
        print(f"Error fetching poll: {x.status_code}")
        continue

    try:
        response = x.json()
        print(response['question'])
    except json.JSONDecodeError:
        print("Invalid response received.")
        continue

    answer = input("Please input your answer, yes or no (y/n): ").lower()
    jsnoAns = {}

    if answer == 'y':
        jsnoAns['vote'] = 'GREEN'
    elif answer == 'n':
        jsnoAns['vote'] = 'RED'
    else:
        print("Invalid answer")
        continue

    # Serialize your dictionary to a JSON string
    json_data = json.dumps(jsnoAns)
    r = requests.post(post_url, data=json_data, headers=headers)

    # Check if POST request was successful
    if r.status_code == 200:
        print("Vote submitted successfully!")
    else:
        print(f"Error submitting vote: {r.status_code}")

    print(f"Yes: {r.json()['greenCount']}, No: {r.json()['redCount']}")
