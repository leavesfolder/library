var myChart = echarts.init(document.getElementById('main'));


var labelFromatter = {
    normal : {
        label : {
            formatter : function (params){
                return   '共91人\n'+params.value
            },
            textStyle: {
                baseline : 'top'
            }
        }
    },
}
var labelBottom = {
    normal : {
        color: '#ccc',
        label : {
            show : true,
            position : 'center'
        },
        labelLine : {
            show : false
        }
    },
    emphasis: {
        color: 'rgba(0,0,0,0)'
    }
};

function getData(queryFlag) {
    var map ;
    $.ajax({
        url:"/library/autoEntry/SysLoginLog/queryLoginLog.do",
        type:"post",
        data:{"queryFlag":queryFlag},
        async:false,
        success:function(data){
            map = data.data ;
            // eval('('+arr+')');
            //a = JSON.stringify(arr);
        }
    });
    return map;
}
var radius = [40, 55];

option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: '20%',
        y:'20%',
        data:[
            '小学生','中学生','大学生','中年学者','老年人',
            '其他'
        ]
    },
    /*title : {
        text: 'The App World',
        subtext: 'from global web index',
        x: 'center'
    },*/
    name:'访问来源',
    type:'pie',
    radius: ['60%', '80%'],
    avoidLabelOverlap: false,
    label: {
        normal: {
            show: false,
            position: 'center'
        },
        emphasis: {
            show: true,
            textStyle: {
                fontSize: '24',
                fontWeight: 'bold'
            }
        }
    },
    labelLine: {
        normal: {
            show: false
        }
    },
    series : [
        {

            type : 'pie',
            center : ['70%', '60%'],
            radius : radius,
            x: '0%', // for funnel
            data :(function () {
                var arr = [];
                var map = getData("role");
                 arr.push({name:'小学生', value:map["01"]});
                 arr.push({name:'中学生', value:map["02"]});
                 arr.push({name:'大学生', value:map["03"]});
                 arr.push({name:'中年学者', value:map["04"]});
                 arr.push({name:'老年人', value:map["05"]});
                 arr.push({name:'其他', value:map["06"]});
                return arr;
            })(),
        }
    ]
};


 myChart.setOption(option);

/*中国地图*/
var option = {
   	title: {
                left:'center',

    },
    tooltip: {
        trigger: 'item'
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data:['科技类','文学小说类','专业学术类']
    },
    visualMap: {
        min: 0,
        max: 40,
        left: 'left',
        top: 'center',
        text: ['高','低'],           // 文本，默认为数值文本
        calculable: true
    },
    toolbox: {
        show: true,
        orient: 'vertical',
        left: 'right',
        top: 'center',
        feature: {
            dataView: {readOnly: false},
            restore: {},
            saveAsImage: {}
        }
    },
    series: [
        {
            name: '中国',
            type: 'map',
            mapType: 'china',
            selectedMode : 'multiple',
            layoutCenter: ['50%', '30%'],
// 如果宽高比大于 1 则宽度为 100，如果小于 1 则高度为 100，保证了不超过 100x100 的区域
            layoutSize: 300,
            label: {
                normal: {
                    show: false
                },
                emphasis: {
                    show: false
                }
            },
            itemStyle:{
            	emphasis:{
                		areaColor:'#eeeeee'
                	}
            },
            data:(function () {
                var arr = [];
                var map = getData("city");
                for(var key in map){
                    //key是属性,object[key]是值
                    var value = map[key];//往数组中放属性
                    console.log(key,value);
                    arr.push({name:key,value:value});
                }
                return arr;
            })(),
        }
    ]
};
        var myChart = echarts.init(document.getElementById('zgdt'));       
                    
        myChart.setOption(option);  
        

/*近期活动*/
option = {
    color: ['#3398DB'],
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : ['话轨', '人轨', '车轨', '话轨', '网轨'],
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'轨迹次数',
            type:'bar',
            barWidth: '60%',
            data:[20, 15, 10, 34, 5]
        }
    ]
};

var myChart = echarts.init(document.getElementById('zjhddy'));       
                    
     myChart.setOption(option);  





