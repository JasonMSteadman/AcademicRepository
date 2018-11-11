using Microsoft.AspNetCore.SignalR;
using System.Threading.Tasks;
using System.Collections.Generic;


namespace SignalRChat.Hubs
{
    public class ChatHub : Hub
    {
        static int[] poll = new int[4];
        private static List<string> lSessions = new List<string>();

        public async Task SendMessage(int pick)
        {
            switch (pick)
            {
                case 1:
                    ++poll[0];
                    break;
                case 2:
                    ++poll[1];
                    break;
                case 3:
                    ++poll[2];
                    break;
                case 4:
                    ++poll[3];
                    break;
            }
           
            await Clients.All.SendAsync("ReceiveMessage", poll[0], poll[1], poll[2], poll[3]);
        }
    }
}