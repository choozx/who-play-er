new Vue({
    el: '#app',
    data: {
        playerName: '',
        playerStats: null
    },
    methods: {
        fetchPlayerStats() {
            // 예시 API 요청 (실제 API로 변경)
            fetch(`/api/player-stats?name=${this.playerName}`)
                .then(response => response.json())
                .then(data => {
                    this.playerStats = data;
                });
        }
    },
    template: `
        <div class="container">
            <h1>OP.GG - Player Statistics</h1>
            <div class="form-group">
                <label for="playerName">Player Name</label>
                <input type="text" v-model="playerName" id="playerName" class="form-control" placeholder="Enter Player Name">
            </div>
            <button @click="fetchPlayerStats" class="btn btn-primary">Get Player Stats</button>

            <div v-if="playerStats" class="mt-4">
                <h3>{{ playerStats.name }}'s Stats</h3>
                <p>Wins: {{ playerStats.wins }}</p>
                <p>Losses: {{ playerStats.losses }}</p>
                <p>Rank: {{ playerStats.rank }}</p>
                <p>Favorite Champion: {{ playerStats.favoriteChampion }}</p>
            </div>
        </div>
    `
});
