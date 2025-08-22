import {Component, OnInit} from '@angular/core';
import {ChatService} from '../../../services/services/chat.service';
import {ChatResponse} from '../../../services/models/chat-response';
import {ChatListComponent} from '../chat-list/chat-list.component';

@Component({
  selector: 'app-main',
  imports: [
    ChatListComponent
  ],
  templateUrl: './main.component.html',
  styleUrl: './main.component.scss'
})
export class MainComponent implements OnInit{

  chats: Array<ChatResponse> = [];

  constructor(private chatService: ChatService) {
  }

  ngOnInit() {
    this.getAllChats()
  }

  private getAllChats() {
    this.chatService.getChatsByReceiverId()
      .subscribe({
        next:(res => {
          this.chats = res;
        })
      })
  }
}
