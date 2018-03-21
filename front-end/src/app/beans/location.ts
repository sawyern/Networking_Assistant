export class Response{
  results:Results[];
}

class Results{
  geometry:Geometry;
}

class Geometry{
  location:Location;
}

class Location{
  lat;
  lng;
}
