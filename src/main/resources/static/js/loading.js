var opts = {
    lines: 14, // The number of lines to draw
    length: 0, // The length of each line
    width: 15, // The line thickness
    radius: 37, // The radius of the inner circle
    scale: 1.4, // Scales overall size of the spinner
    corners: 1, // Corner roundness (0..1)
    color: '#ffffff', // CSS color or array of colors
    fadeColor: 'transparent', // CSS color or array of colors
    speed: 1.1, // Rounds per second
    rotate: 35, // The rotation offset
    animation: 'spinner-line-fade-quick', // The CSS animation name for the lines
    direction: 1, // 1: clockwise, -1: counterclockwise
    zIndex: 2e9, // The z-index (defaults to 2000000000)
    className: 'spinner', // The CSS class to assign to the spinner
    top: '49%', // Top position relative to parent
    left: '48%', // Left position relative to parent
    shadow: '0 0 0px transparent', // Box-shadow for the lines
    position: 'absolute' // Element positioning
};

var spinner = new Spinner(opts);
var target = $("#loadingDiv").get(0);

