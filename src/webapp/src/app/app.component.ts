import {Component} from '@angular/core';
import {DistinctService} from "./distinct.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'webapp';
  selectedFile: any;
  criteria: string;
  error: string;
  showResult: boolean = false;
  result: any;

  constructor(private distinctService: DistinctService) {
  }

  onFileChanged(event) {
    this.selectedFile = event.target.files[0];
  }

  send() {
    this.distinctService.distinct(this.selectedFile, this.criteria).subscribe(
      (value) => {
        this.result = value;
        this.error = '';
        this.showResult = true;
      },
      (error) => {
        this.error = 'ERROR';
        this.showResult = false;
      }
    );
  }
}
