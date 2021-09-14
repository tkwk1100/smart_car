$(function(){
    let initName = $(".rank_item").eq(0).attr("data-name")
    getItemData(initName);
    $(".item_name").html(initName);

    $(".rank_item").eq(0).addClass("selected");
    $(".rank_item").click(function(){
        let name = $(this).attr("data-name");
        $(".rank_item").removeClass("selected");
        $(this).addClass("selected");
        $(".item_name").html(name);

        getItemData(name);
    });
    let genChart = null;
    let ageChart = null;
    let regionChart = null;
    function getItemData(name) {
        $.ajax({
            type:"get",
            url:"http://localhost:8090/item/gen_all?item="+name,
            success:function(r){
                console.log(r.cnt);
                if(genChart != null)genChart.destroy();
                genChart = new Chart($("#gen_chart"), {
                    type: "pie",
                    data:{
                        labels:["남자", "여자"],
                        datasets:[
                            {
                                lable:"구매비율",
                                data:r.cnt,
                                backgroundColor:['#BDFD60','#FAED6C']
                            }
                        ]
                    }
                })
            }
        })

        $.ajax({
            type:"get",
            url:"http://localhost:8090/item/age?item="+name,
            success:function(r){
                console.log(r.cnt);
                if(ageChart != null)ageChart.destroy();
                ageChart = new Chart($("#age_chart"), {
                    type: "pie",
                    data:{
                        labels:["20대", "30대","40대","50대","60대","70대 이상"],
                        datasets:[
                            {
                                lable:"구매비율",
                                data:r.ageList,
                                backgroundColor:['#FDCF3A','#E6A830','#FAA847','#FD6C3A','#FF4533']
                            }
                        ]
                    }
                })
            }
        })

        $.ajax({
            type:"get",
            url:"/item/score?item="+name,
            success:function(r){
                console.timeLog(r);
                let score = r.score*100;
                score = Math.floor(score);
                score = score / 100;
                $(".score").html(score);
                $(".item_score_prog").css("width", score/5*100+"%");
            }
        })

        $.ajax({
            type:"get",
            url:"/item/region?item="+name,
            success:function(r) {
                console.log(r);
                if(regionChart != null) regionChart.destroy();
                regionChart = new Chart($("#region_chart"), {
                    type:"bar",
                    data:{
                        labels:r.regions,
                        datasets:[
                            {
                                label:"지역 별 차량용품 구매 수",
                                data:r.item_counts
                            }
                        ]
                    }
                })
            }
        })
    }
})