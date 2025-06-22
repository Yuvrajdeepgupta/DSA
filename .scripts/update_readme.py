import requests
from datetime import datetime

USERNAME = "gupta__ji"
README_PATH = "README.md"

def fetch_leetcode_stats(username):
    url = f"https://leetcode-stats-api.herokuapp.com/{username}"
    resp = requests.get(url)
    resp.raise_for_status()
    return resp.json()

def update_readme(data):
    with open(README_PATH, "r", encoding="utf-8") as f:
        lines = f.readlines()

    start = lines.index("<!-- LEETCODE-STATS-START -->\n") + 1
    end   = lines.index("<!-- LEETCODE-STATS-END -->\n")

    # Build the new stats block
    stats = (
        f"- 🧠 Total Solved: **{data['totalSolved']} / {data['totalQuestions']}**\n"
        f"- 🟢 Easy: **{data['easySolved']}**\n"
        f"- 🟡 Medium: **{data['mediumSolved']}**\n"
        f"- 🔴 Hard: **{data['hardSolved']}**\n"
        f"- 🏆 Ranking: **#{data['ranking']}**\n"
        f"- ⏰ Last updated: {datetime.utcnow():%Y-%m-%d %H:%M:%S} UTC\n"
    )

    # Replace the section
    lines[start:end] = [stats]
    with open(README_PATH, "w", encoding="utf-8") as f:
        f.writelines(lines)

if __name__ == "__main__":
    stats = fetch_leetcode_stats(USERNAME)
    update_readme(stats)
