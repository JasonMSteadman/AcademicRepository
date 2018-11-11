var poll = {
    oneJob:0,
    twoJob:0,
    threeJob:0,
    moreJob:0
}

function upDatePoll(value) {
    if (value == 1) {
        ++poll.oneJob;
        alert(poll.oneJob);
    }
    else if (value == 2) {
        ++poll.twoJob;
    }
    else if (value == 3) {
        ++poll.threeJob;
    }
    else if (value == 4) {
        ++poll.moreJob;
    }
    alert(poll.oneJob + ", " + poll.twoJob + ", " + poll.threeJob + ", " + poll.moreJob);
}

    // Load google charts
    google.charts.load('current', {'packages': ['corechart'] });
    google.charts.setOnLoadCallback(drawChart);

    // Draw the chart and set the chart values
function drawChart(j1, j2, j3, jm) {

        var data = google.visualization.arrayToDataTable([
        ['Jobs', 'Stats'],
        ['One', j1],
        ['Two', j2],
        ['Three', j3],
        ['More', jm]
    ]);

    // Optional; add a title and set the width and height of the chart
        var options = {'title': 'Jobs in the last year', 'width': 550, 'height': 400 };

        // Display the chart inside the <div> element with id="piechart"
    var chart = new google.visualization.PieChart(document.getElementById('piechart'));
    chart.draw(data, options);
}
