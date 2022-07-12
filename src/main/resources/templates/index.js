const BASE_URL = 'http://localhost:8080';

let pageSelected = 1;
let unitSelected = 1;


function init(){
    getAllUnits();
}

function resetElementList(){
    document.getElementById("pageInfo").innerHTML = "";
}

function resetPageList(){
    document.getElementById("pagesList").innerHTML = "";
}

async function getAllPages(){
    let htmlResponse = "<ul class='list-group'>";
    try {
    const response = await axios.get(`${BASE_URL}/pages/get-all`);
    response.data.forEach(page => {
        htmlResponse += "<li class='list-group-item'><a onclick='getPage("+page.id+")'>"+page.name+"</a> | <a onclick='deletePage("+page.id+")'><i class='bi bi-x-circle'></i></a></li>";
    });
    htmlResponse += "</ul>";
    document.getElementById("pagesList").innerHTML = htmlResponse;
    return response.data;
    } catch (errors) {
    console.error(errors);
    }
}

async function getAllPagesByUnitId(unitId){
    unitSelected = unitId;
    let htmlResponse = "<ul class='list-group'>";
    try {
    const response = await axios.get(`${BASE_URL}/pages/get-all-by-unit/`+ unitId);
    response.data.forEach(page => {
        htmlResponse += "<li class='list-group-item'><a onclick='getPage("+page.id+")'>"+page.name+"</a> | <a onclick='deletePage("+page.id+")'><i class='bi bi-x-circle'></i></a></li>";
    });
    htmlResponse += "</ul>";
    document.getElementById("pagesList").innerHTML = htmlResponse;
    return response.data;
    } catch (errors) {
    console.error(errors);
    }
}

async function getPage(id){
    pageSelected = id;
    let htmlResponse = "";
    try {
    const response = await axios.get(`${BASE_URL}/pages/`+id);
    console.log(response.data);
    htmlResponse += response.data.name;
    htmlResponse += "<ul class='list-group'>";
    /* response.data.elements.sort(function(a, b){return a.position - b.position}); */
    response.data.elements.forEach(element => {
        
        if(element.elementCarouselDatas){
            htmlResponse += "<li class='list-group-item'>"+element.type+" position: "+element.position+" | <a onclick='deleteElement("+element.id+")'><i class='bi bi-x-circle'></i></a> - <a onclick='addElementCarouselData("+element.id+")'><i class='bi bi-plus-circle'></i></a>";
            htmlResponse += "<ul>";
            element.elementCarouselDatas.forEach(data => {
                htmlResponse += "<li class='list-group-item'>"+data.type+"| <a onclick='deleteElementCarouselData("+data.id+")'><i class='bi bi-x-circle'></i></a></li>";
            })
            htmlResponse += "</li>";
            htmlResponse += "</ul>";
        }else{
            htmlResponse += "<li class='list-group-item'>"+element.type+" position: "+element.position+" | <a onclick='deleteElement("+element.id+")'><i class='bi bi-x-circle'></i></a></li>";
        }
    });
    htmlResponse += "</ul>";
    document.getElementById("pageInfo").innerHTML = htmlResponse;
    return response.data;
    } catch (errors) {
    console.error(errors);
    }
}


async function addPage(){
    try {
    const page = {"name": "New Page"};
    const response = await axios.post(`${BASE_URL}/pages`,page);
    console.log(response);
    } catch (errors) {
    console.error(errors);
    }
    getAllPagesByUnitId(unitSelected);
}

async function deletePage(id){
    try {
    const response = await axios.delete(`${BASE_URL}/pages/`+id);
    console.log(response);
    } catch (errors) {
    console.error(errors);
    }
    getAllPages();
}


async function addElementImage(){
    try {
    const element = {
        "position": 200,
        "page": {
            "id": pageSelected
        },
        "type": "IMAGE",
        "url" : "IMAGE URL 200"
    };
    const response = await axios.post(`${BASE_URL}/elements/image`,element);
    console.log(response);
    } catch (errors) {
    console.error(errors);
    }
    getPage(pageSelected);
}

async function addElementCarousel(){
    try {
    const element = {
        "position": 99,
        "page": {
            "id": pageSelected
        },
        "type": "CAROUSEL",
        "title" : "CAROUSEL 300"
    };
    const response = await axios.post(`${BASE_URL}/elements/carousel`,element);
    console.log(response);
    } catch (errors) {
    console.error(errors);
    }
    getPage(pageSelected);
}

async function addElementCarouselData(elementCarouselId){
    try {
    const element = {
        "type": "OO",
        "title" : "CAROUSEL DATA 300",
        "elementCarousel": {
            "id": elementCarouselId
        } 
    };
    const response = await axios.post(`${BASE_URL}/elements/carousel-data`,element);
    console.log(response);
    } catch (errors) {
    console.error(errors);
    }
    getPage(pageSelected);
}

async function deleteElementCarouselData(id){
    try {
    const response = await axios.delete(`${BASE_URL}/elements/carousel-data/`+id);
    console.log(response);
    } catch (errors) {
    console.error(errors);
    }
    getPage(pageSelected);
}


async function deleteElement(id){
    try {
    const response = await axios.delete(`${BASE_URL}/elements/`+id);
    console.log(response);
    } catch (errors) {
    console.error(errors);
    }
    getPage(pageSelected);
}


async function getAllUnits(){
    let htmlResponse = "<ul class='list-group'>";
    try {
    const response = await axios.get(`${BASE_URL}/units/get-all`);
    response.data.forEach(unit => {
        htmlResponse += "<li class='list-group-item'><a onclick='getAllPagesByUnitId("+unit.id+")'>"+unit.name+"</a> | <a onclick='deleteUnit("+unit.id+")'><i class='bi bi-x-circle'></i></a></li>";
    });
    htmlResponse += "</ul>";
    document.getElementById("unitList").innerHTML = htmlResponse;
    return response.data;
    } catch (errors) {
    console.error(errors);
    }
}


async function addUnit(){
    try {
    const unit = {"name": "New Unit"};
    const response = await axios.post(`${BASE_URL}/units`,unit);
    console.log(response);
    } catch (errors) {
    console.error(errors);
    }
    
    resetPageList();
    resetElementList();
    getAllUnits();
}