function barchart(MyUserObject){
        //document.getElementById('spinner').style.display='none';
        //var MyUserObject = @D3DataNPost;
        var w = (Object.keys(MyUserObject).length)* 21;
        console.log("This is how many objects we have:" + Object.keys(MyUserObject).length);
        var h = 480;
        var barPadding = 1;
        document.getElementById('inner').style.width = w + "px";
        document.getElementById('svgframe').style.width = w + "px";
        contextMenuShowing = false;
        var div = d3.select("body").append("div")
            .attr("class", "tooltip")
            .style("opacity", 0);
        var canvas = d3.select('#svgframe')
            .append("svg")
            .attr("width", w)
            .attr("height", h);
            var bars = canvas.selectAll("g")
                        .data(MyUserObject)
                        .enter()
                        .append("g")
                        bars.append("rect")
                            .filter(function(d) { return d.numberofposts})
                            .attr('class', 'bar')
                            .attr("width", 23)
                            .attr("height", function(d){
                            if(d.numberofposts < 10)
                            {return d.numberofposts}
                            else
                            {return d.numberofposts/13}
                            })
                            .attr("x", function(d, i){ return i * 25})
                            .attr("y", function(d){
                            if(d.numberofposts < 10)
                            {return h-(d.numberofposts)}
                            else
                            {return h-(d.numberofposts/13)}
                            })
                            .attr("fill", function(d) {
                            return "#668cff";
                            })

                            .on("mouseover", function(d) {
                            div.transition()
                                .duration(100)
                                .style("opacity", .9);
                            div.html("#Posts: "+d.numberofposts)
                                .style("left", (d3.event.pageX) + "px")
                                .style("top", (d3.event.pageY - 28) + "px");
                            })
                            .on("mouseout", function(d) {
                                div.transition()
                                    .duration(300)
                                    .style("opacity", 0);
                            })

                            .on("click",function (d,i) {
                                if(contextMenuShowing) {
                                    d3.event.preventDefault();
                                    d3.select(".popup").remove();
                                    contextMenuShowing = false;
                                } else {
                                    d3_target = d3.select(d3.event.target);
                                    if (d3_target.classed("bar")) {
                                        d3.event.preventDefault();
                                        contextMenuShowing = true;
                                        d = d3_target.datum();

                                        // Build the popup
                                        popup = d3.select('#svgframe')
                                        .append("div")
                                        .attr("class", "popup")
                                        .style("left", "540px")
                                        .style("top", "80px")
                                        popup.append("h2").text(d.numberofposts)
                                        popup.append("p").text(
                                            "The " + d.numberofposts + " division (wearing " + d.numberofposts + " uniforms) had " + d.numberofposts + " casualties during the show's original run.")
                                        popup.append("p")
                                        .text(d.numberofposts);
                                    }
                                }
                            });

                        bars.append("text")
                            .filter(function(d) { return d.numberofposts})
                            .text(function(d) {
                            return (d.username);
                            })
                            .attr("x", -h)
                            .attr("y", function(d, i){ return i * 25})
                            .attr("fill", "#330022")
                            .attr("font-family", "sans-serif")
                            .attr("font-size", "12px")
                            .attr("font-weight", "bold")
                            .style("text-anchor", "start")
                            .attr("dx", ".95em")
                            .attr("dy", ".95em")
                            .attr("transform", "rotate(-90)");
            console.log(d3);
            }
