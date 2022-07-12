import { NbMenuItem } from '@nebular/theme';

export const MENU_ITEMS: NbMenuItem[] = [
  {
    title: 'E-commerce',
    icon: 'shopping-cart-outline',
    link: '/pages/dashboard',
    home: true,
  },
  {
    title: 'IoT Dashboard',
    icon: 'home-outline',
    link: '/pages/iot-dashboard',
  },
  {
    title: 'Kafka Admin',
    icon: 'home-outline',
    children: [
      {
        title: 'Overview',
        link: '/pages/kafka-admin/overview',
      },
    ]
  }
];
