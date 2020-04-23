import { PipeTransform, Pipe } from '@angular/core';
import { CurrentUserService } from '../services/current-user.service';

@Pipe({ name: 'recogniseMe' })
export class RecogniseMePipe implements PipeTransform {

    constructor(private user: CurrentUserService) { }

    transform(value: any) {
        if (value === this.user.username) {
            return 'Me';
        } else return value;
    }
}