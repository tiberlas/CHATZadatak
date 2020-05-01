import { Injectable } from '@angular/core';
import { UserStatus } from '../model/user-status';
import { throwError } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class LoggedUsersService {

    private loggedInUsers: string[];

    public getLoggedInUsers(): string[] {
        return this.loggedInUsers;
    }

    public updateActiveUsers(users: string[]) {
        this.loggedInUsers = users;
    }

    public userstatusChanged(userStatus: UserStatus): void {
        if (userStatus.isActive === false) {
            this.removeUser(userStatus.username);
        } else {
            this.addUser(userStatus.username);
        }
    }

    private removeUser(username: string): void {
        let i = this.loggedInUsers.indexOf(username);

        if (i !== -1) {
            this.loggedInUsers.splice(i, 1);
        }
    }

    private addUser(username: string): void {
        if (this.loggedInUsers.includes(username) === false) {
            this.loggedInUsers.push(username);
        }
    }
}