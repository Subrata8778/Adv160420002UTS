<?php
$arr = array (
array (
    "name"=>"Dr. John Smith",    
    "specialist"=>"Cardiologist",    
    "location"=>"New York",    
    "desc"=>"Dr. John Smith is a highly experienced cardiologist with over 20 years of experience. He specializes in diagnosing and treating heart conditions.",    
    "photoUrl"=>"https://hips.hearstapps.com/hmg-prod/images/portrait-of-a-happy-young-doctor-in-his-clinic-royalty-free-image-1661432441.jpg?crop=0.66698xw:1xh;center,top&resize=1200:*"  
),
array (
    "name"=>"Dr. Jane Doe",    
    "specialist"=>"Neurologist",    
    "location"=>"Los Angeles",    
    "desc"=>"Dr. Jane Doe is a renowned neurologist who has been featured in several medical journals for her groundbreaking research on brain disorders.",    
    "photoUrl"=>"https://img.freepik.com/free-photo/pleased-young-female-doctor-wearing-medical-robe-stethoscope-around-neck-standing-with-closed-posture_409827-254.jpg"
), 
array (
    "name"=>"Dr. Michael Lee",    
    "specialist"=>"Oncologist",    
    "location"=>"Chicago",    
    "desc"=>"Dr. Michael Lee is an expert in the field of oncology and has helped countless patients fight cancer. He is known for his compassionate and personalized approach to treatment.",    
    "photoUrl"=>"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRu3VVDOvmLU3rE8hRipaGlKH0mjAvmk6CU0w&usqp=CAU"
),
array (
    "name"=>"Dr. Sarah Jones",   
    "specialist"=>"Pediatrician",    
    "location"=>"Houston",    
    "desc"=>"Dr. Sarah Jones is a pediatrician who has dedicated her career to helping children lead healthy and happy lives. She is known for her warm and friendly demeanor, which helps put her young patients at ease.",   
    "photoUrl"=>"https://www.onlineshs.com/wp-content/uploads/2022/12/shutterstock_1901822248-1-1.png"
), 
array (
    "name"=>"Dr. David Kim",    
    "specialist"=>"Dermatologist",    
    "location"=>"Miami",   
    "desc"=>"Dr. David Kim is a skilled dermatologist who is passionate about helping his patients achieve healthy and beautiful skin. He is known for his expertise in the latest cosmetic treatments and procedures.", 
    "photoUrl"=>"https://yt3.googleusercontent.com/ytc/AGIKgqPmK1ByzeeLa49q6DJoTKm0_2kTHVXaIpaV25FSYw=s900-c-k-c0x00ffffff-no-rj"
)
);

$result = null;
if(isset($_GET["doctor_list"])){
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
    $result = ["result" => "success", "message" => "Doctor not found"];
    echo json_encode($result);
}
?>