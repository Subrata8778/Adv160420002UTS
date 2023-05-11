<?php
$arr = array (
array(
    "name"=>"Paracetamol",
    "price"=>10000,
    "stock"=>100,
    "desc"=>"Paracetamol is a commonly used pain reliever and fever reducer.",
    "photoUrl"=>"https://images.k24klik.com/product/apotek_online_k24klik_20210624013902359225_paracetamol-triman.jpg"
),
array(
    "name"=>"Ibuprofen",
    "price"=>12000,
    "stock"=>75,
    "desc"=>"Ibuprofen is a nonsteroidal anti-inflammatory drug (NSAID) used to relieve pain and reduce fever.",
    "photoUrl"=>"https://images.k24klik.com/product/large/apotek_online_k24klik_20211210093212359225_IBUPROFEN-TRIMAN-400MG-TAB-100S-removebg-preview.png"
),
array(
    "name"=>"Aspirin",
    "price"=>8000,
    "stock"=>50,
    "desc"=>"Aspirin is a medication used to treat pain, fever, or inflammation.",
    "photoUrl"=>"https://img2.beritasatu.com/cache/beritasatu/910x580-2/1623209335.jpg"
),
array(
    "name"=>"Acetaminophen",
    "price"=>9000,
    "stock"=>80,
    "desc"=>"Acetaminophen is a medication used to treat pain and fever.",
    "photoUrl"=>"https://www.drugwatch.com/wp-content/uploads/Acetaminophen.jpg"
),
array(
    "name"=>"Naproxen",
    "price"=>15000,
    "stock"=>60,
    "desc"=>"Naproxen is a nonsteroidal anti-inflammatory drug (NSAID) used to relieve pain and reduce inflammation.",
    "photoUrl"=>"https://www.saridon.com.ph/sites/g/files/vrxlpx39561/files/2020-10/Saridon%20Sarimax%20275%20Product%20%20720x520_01102020.png"
)
);

$result = null;
if(isset($_GET["medicine_list"])){
    $result = $arr;
    echo json_encode($result);
}else if (isset($_GET["name"])){
    $name = $_GET["name"];
    foreach($arr as $medicine){
        if($medicine["name"] == $name){
            $result = $medicine;
            echo json_encode($result);
            break;
        }
    }
}else{
    $result = ["result" => "success", "message" => "Medicine not found"];
    echo json_encode($result);
}
?>