import { Pipe, PipeTransform } from "@angular/core";
@Pipe({
  name: "eventFilter"
})
export class EventFilter implements PipeTransform {
  transform(items: any[], searchText: string): any[] {
    if (!items) return [];
    if (!searchText) return items;
    searchText = searchText.toLowerCase();
    return items.filter(it => {
      return it.name.toLowerCase().includes(searchText);
    });
  }
}

@Pipe({
  name: "starFilter"
})
export class StarFilter implements PipeTransform {
  transform(items: any[], searchText: string): any[] {
    if (!items) return [];
    if (!searchText) return items;
    searchText = searchText.toLowerCase();
    return items.filter(it => {
      let keys = Reflect.ownKeys(it);
      console.log(keys);
      for (let key in keys) {
        if (it[keys[key]] == null) {
          return false;
        } else if (it[keys[key]].toString().toLowerCase().includes(searchText)) {
            return true;
        }
      }
      return false;
    });
  }
}
